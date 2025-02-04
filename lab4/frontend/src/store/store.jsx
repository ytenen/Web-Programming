import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../slices/AuthSlice';
import registerReducer from '../slices/RegisterSlice';
import resultReducer from '../slices/ResultSlice';

const store = configureStore({
    reducer: {
        auth: authReducer,
        register: registerReducer,
        results: resultReducer,
    },
});

export default store;
