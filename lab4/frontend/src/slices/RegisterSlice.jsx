
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from "axios";

export const registerUser = createAsyncThunk(
    'register/registerUser',
    async (userData, { rejectWithValue }) => {
        try {
            const response = await axios.post(
                'http://localhost:8080/hueb4/api/controller/register',
                userData,
                {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }
            );
            console.log('Response:', response.data);
            return response.data;
        } catch (error) {
            return rejectWithValue(error.response?.data || 'Registration failed');
        }
    }
);



const registerSlice = createSlice({
    name: 'register',
    initialState: {
        username: null,
        userId: null,
        loading: false,
        error: null,
    },
    reducers: {
        clearError(state) {
            state.error = null;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(registerUser.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(registerUser.fulfilled, (state, action) => {
                console.log('Payload:', action.payload.username);
                state.loading = false;
                state.username = action.payload.username;
                state.userId = action.payload.id;
            })
            .addCase(registerUser.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            });
    },
});

export const { clearError } = registerSlice.actions;
export default registerSlice.reducer;
