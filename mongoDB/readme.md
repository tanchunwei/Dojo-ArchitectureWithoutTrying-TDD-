## Set up MongoDB shard using docker container

### Setup mongo DB in docker
```$xslt
docker-compose up -d
```

### Init replica set  (Only need to config once)
##### For config server
######Connect to first config server
```
mongo mongodb://localhost:40001
```
######Init replicaset of config sever together
```$xslt
rs.initiate(
    {
        _id: "cfgrs",
        configsvr: true,
        members: [
            { _id: 0, host: "cfgsvr1:27017" },
            { _id: 1, host: "cfgsvr2:27017" },
            { _id: 2, host: "cfgsvr3:27017" }
        ]
    }
)

rs.status()
```

##### For shard server
######Connect to first shard1 server
```
mongo mongodb://localhost:50001
```
######Init replicaset of shard1 server together
```$xslt
rs.initiate(
    {
        _id: "shard1rs",
        members: [
            { _id : 0, host : "shard1svr1:27017" },
            { _id : 1, host : "shard1svr2:27017" },
            { _id : 2, host : "shard1svr3:27017" }
        ]
    }
)

rs.status()
```

##### For mongo router
######Connect to mongos
```
mongo mongodb://localhost:60000
```

######Add shard
```
sh.addShard("shard1rs/shard1svr1:27017,shard1svr2:27017,shard1svr3:27017")
sh.status()
```

##Mongo DB OPS Manager
####Create docker image
```
docker build -f Dockerfile -t opsmanager:latest .
```
####Create docker container
```
docker run --name opsmanager --rm -i -t opsmanager bash
```
####Create user in mongo DB
```
mongo mongodb://localhost:60000
```
```
use admin

db.createUser({  
 user:"mongodbadmin",
 pwd:"password",
 roles:[
    {
        "role" : "readWriteAnyDatabase",
        "db" : "admin"
    },
    {
        "role" : "dbAdminAnyDatabase",
        "db" : "admin"
    },
    {
        "role" : "clusterAdmin",
        "db" : "admin"
    },
    {
        "role" : "clusterMonitor",
        "db" : "admin"
    }
 ],
 mechanisms:[  
  "SCRAM-SHA-1"
 ]
})
```