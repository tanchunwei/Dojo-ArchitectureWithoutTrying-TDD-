#To check minikube status
#minikube status

#Make sure minikube is started
#minikube start

#To use ingress
#minikube addons enable ingress

#Nginx ingress controller will be installed in kube-system namespace
#kubectl get pod -n kube-system

#To see the instances created [depl-name]-[replicaset-id]-[pod-id]
#kubectl get deployment
#kubectl get replicaset
#kubectl get pod

#To edit the config file
#kubectl edit deployment [name]

#To get logs
#kubectl logs [pod-id]

#To get additional information of the pod log
#kubectl describe pod [pod-id]

#example to go into console
#kubectl exec -it <pod-id> -- bin/bash

#example to go into posgres console
# <exec bash into posgres pod console>
#psql -h localhost -U postgres --password -p 5432

#hint to deploy code, scale down to 0 then scale up again or rollout restart (**note all the logs will be destroy as well)
#kubectl scale deployment [deployment name] --replicas=0
# or
#kubectl rollout restart deployment [deployment name]

#depoly configuration
kubectl apply -f db-storage.yml
kubectl apply -f db-deployment.yml
kubectl apply -f db-service.yml
kubectl apply -f pos-deployment.yml
kubectl apply -f pos-service.yml
kubectl apply -f webfrontend-deployment.yml
kubectl apply -f webfrontend-service.yml
kubectl apply -f ingress.yml