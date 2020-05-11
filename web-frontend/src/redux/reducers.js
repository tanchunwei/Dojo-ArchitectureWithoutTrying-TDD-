import clientAuthReducer from './reducers/clientAuth';
import {combineReducers} from 'redux';

const allReducers = combineReducers({
   clientAuth : clientAuthReducer
});

export default allReducers;