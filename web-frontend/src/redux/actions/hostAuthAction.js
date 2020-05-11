export const HOST_AUTHENTICATION = 'hostAuthenticate'
export const HOST_SIGNOUT = 'hostSignout'

export const hostAuthAction = () => {
    return {
        type: HOST_AUTHENTICATION
    };
};

export const hostSignoutAction = () => {
    return {
        type: HOST_SIGNOUT
    };
};