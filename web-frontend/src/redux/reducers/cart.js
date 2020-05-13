import { ADD_CART, SET_CART } from './../actions/cartAction'

const cartReducer = (state = [], action) => {
    switch(action.type){
        case ADD_CART:
            state.push({
                clientID : action.payload.cid,
                barcode : action.payload.barcode
            });
            return state
        case SET_CART:
            state = action.payload.cart
            return state
         default:
            return state;
    }
}

export default cartReducer;
