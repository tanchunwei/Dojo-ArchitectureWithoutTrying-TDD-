import { HOST_AUTHENTICATION, HOST_SIGNOUT} from './../actions/hostAuthAction'

const hostAuthReducer = (state = {
    isAuthenticated : false
}, action) => {
    switch(action.type){
        case HOST_AUTHENTICATION:
            return {
                isAuthenticated : true
            };
        case HOST_SIGNOUT:
            return {
                isAuthenticated : false
            };
         default:
            return state;
    }
}

export default hostAuthReducer;
