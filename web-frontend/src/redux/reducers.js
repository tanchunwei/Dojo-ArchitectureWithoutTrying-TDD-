import clientAuthReducer from './reducers/clientAuth';
import hostAuthReducer from './reducers/hostAuth';
import cartReducer from './reducers/cart';
import {combineReducers} from 'redux';

const allReducers = combineReducers({
   clientAuth : clientAuthReducer,
   hostAuth : hostAuthReducer,
   cart : cartReducer
});

export default allReducers;