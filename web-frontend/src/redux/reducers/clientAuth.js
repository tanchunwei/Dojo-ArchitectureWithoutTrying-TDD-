import { CLIENT_AUTHENTICATION, CLIENT_SIGNOUT} from './../actions/clientAuthAction'

const clientAuthReducer = (state = {
    isAuthenticated : false,
    clientID : ""
}, action) => {
    switch(action.type){
        case CLIENT_AUTHENTICATION:
            return {
                isAuthenticated : true,
                clientID : action.payload.cid
            };
        case CLIENT_SIGNOUT:
            return {
                isAuthenticated : false,
                clientID : ""
            };
         default:
            return state;
    }
}

export default clientAuthReducer;
