import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';


export const sendCoordinates = createAsyncThunk(
    'results/sendCoordinates',
    async ({ x, y, r, userId }, { rejectWithValue }) => {
        try {
            const response = await axios.post(
                'http://localhost:8080/hueb4/api/controller/results',
                {
                    x,
                    y,
                    r,
                    userId,
                },
                {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }
            );
            return response.data;
        } catch (error) {
            return rejectWithValue(error.response?.data || 'Failed to send coordinates');
        }
    });



export const fetchUserResults = createAsyncThunk(
    'results/fetchUserResults',
    async (userId, { rejectWithValue }) => {
        try {
            const response = await axios.get(
                `http://localhost:8080/hueb4/api/controller/results/${userId}`,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        Accept: 'application/json',
                    },
                }
            );
            return response.data;
        } catch (error) {
            return rejectWithValue(error.response?.data || 'Failed to fetch results');
        }
    }
);



export const clearUserResults = createAsyncThunk(
    'results/clearUserResults',
    async (userId, { rejectWithValue }) => {
        try {
            const response = await axios.delete(`http://localhost:8080/hueb4/api/controller/results/clear/${userId}`);
            return response.data;
        } catch (error) {
            return rejectWithValue(error.response?.data || 'Failed to clear results');
        }
    }
);

const resultsSlice = createSlice({
    name: 'results',
    initialState: {
        results: [],
        loading: false,
        error: null,
    },
    reducers: {
        clearResults(state) {
            state.results = [];
            state.error = null;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(sendCoordinates.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(sendCoordinates.fulfilled, (state, action) => {
                state.loading = false;
                state.results.push(action.payload);
            })
            .addCase(sendCoordinates.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            })

            .addCase(fetchUserResults.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(fetchUserResults.fulfilled, (state, action) => {
                state.loading = false;
                state.results = action.payload;
            })
            .addCase(fetchUserResults.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            })

            .addCase(clearUserResults.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(clearUserResults.fulfilled, (state) => {
                state.loading = false;
                state.results = [];
            })
            .addCase(clearUserResults.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            });
    },
});


export const { clearResults } = resultsSlice.actions;
export default resultsSlice.reducer;
