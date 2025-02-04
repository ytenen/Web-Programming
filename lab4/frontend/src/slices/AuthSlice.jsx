import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';


export const loginUser = createAsyncThunk(
    'auth/loginUser',
    async (userData, { rejectWithValue }) => {
        try {
            const response = await axios.post('http://localhost:8080/hueb4/api/controller/login', userData);
            console.log('Response:', response.data);
            return response.data;
        } catch (error) {
            return rejectWithValue(error.response?.data || 'Login failed');
        }
    }
);

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        username: null,
        userId: null,
        loading: false,
        error: null,
    },
    reducers: {
        logout: (state) => {
            state.username = null;
            state.userId = null;
            state.error = null;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(loginUser.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(loginUser.fulfilled, (state, action) => {
                console.log('Payload:', action.payload.username);
                console.log('PayloadID:', action.payload.id);
                state.loading = false;
                state.username = action.payload.username;
                state.userId = action.payload.id;
            })
            .addCase(loginUser.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            });
    },
});

export const { logout } = authSlice.actions;
export default authSlice.reducer;
