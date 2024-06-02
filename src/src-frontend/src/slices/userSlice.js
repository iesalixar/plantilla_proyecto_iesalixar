import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { signinService, signupService } from '../service/auht/authService';

export const signin = createAsyncThunk(
    'user/signin',
    async ({ identifier, password }, thunkAPI) => {
        try {
            const response = await signinService(identifier, password);
            return response;
        } catch (error) {
            return thunkAPI.rejectWithValue(error.message);
        }
    }
);

export const signup = createAsyncThunk(
    'user/signup',
    async ({ registrationInfo }, thunkAPI) => {
        try {
            const response = await signupService(registrationInfo);
            return response;
        } catch (error) {
            return thunkAPI.rejectWithValue(error.message);
        }
    }
);

export const checkAuth = createAsyncThunk(
    'user/checkAuth',
    async (_, thunkAPI) => {
        const userData = sessionStorage.getItem('userData');
        if (userData) {
            return JSON.parse(userData);
        }
    }
);

const initialState = {
    user: null,
    token: null,
    loading: false,
    error: null,
};

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        logout(state) {
            state.user = null;
            state.token = null;
            sessionStorage.removeItem('userData');
        }
    },
    extraReducers: (builder) => {
        builder
            .addCase(signin.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(signin.fulfilled, (state, action) => {
                state.loading = false;
                state.token = action.payload.token;
                state.user = action.payload.userDTO;
                sessionStorage.setItem('userData', JSON.stringify({ user: action.payload.userDTO, token: action.payload.token }));
            })
            .addCase(signin.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            })
            .addCase(signup.pending, (state) => {
                state.loading = true;
                state.error = null;
            })
            .addCase(signup.fulfilled, (state, action) => {
                state.loading = false;
                state.token = action.payload.token;
                state.user = action.payload.userDTO;
                sessionStorage.setItem('userData', JSON.stringify({ user: action.payload.userDTO, token: action.payload.token }));
            })
            .addCase(signup.rejected, (state, action) => {
                state.loading = false;
                state.error = action.payload;
            })
            .addCase(checkAuth.fulfilled, (state, action) => {
                if (action.payload) {
                    state.token = action.payload.token;
                    state.user = action.payload.user;
                }
            })
            .addCase(checkAuth.rejected, (state) => {
                state.token = null;
                state.user = null;
            });
    }
});

export const { logout } = userSlice.actions;
export default userSlice.reducer;
