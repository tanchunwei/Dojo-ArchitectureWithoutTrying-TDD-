export const CLIENT_AUTHENTICATION = 'clientAuthenticate'
export const CLIENT_SIGNOUT = 'clientSignout'

export const clientAuthAction = (clientID) => {
    return {
        type: CLIENT_AUTHENTICATION,
        payload: {cid: clientID}
    };
};

export const clientSignoutAction = () => {
    return {
        type: CLIENT_SIGNOUT
    };
};