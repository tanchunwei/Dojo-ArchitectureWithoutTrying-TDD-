export const ADD_CART = 'addCart'
export const SET_CART = 'setCart'

export const addCartAction = (clientID, barcode) => {
    return {
        type: ADD_CART,
        payload: {cid: clientID, barcode: barcode}
    };
};

export const setCartAction = (cart) => {
    return {
        type: SET_CART,
        payload: {cart: cart}
    };
};