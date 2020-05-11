import clientAuthReducer from './reducers/clientAuth';
import hostAuthReducer from './reducers/hostAuth';
import {combineReducers} from 'redux';

const allReducers = combineReducers({
   clientAuth : clientAuthReducer,
   hostAuth : hostAuthReducer
});

export default allReducers;