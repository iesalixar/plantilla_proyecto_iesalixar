const signupService = async (registrationInfo) => {
    try {
        const response = await fetch('http://localhost:8080/api/v1/auth/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(registrationInfo),
        });

        const contentType = response.headers.get('Content-Type');

        if (!response.ok) {
            if (contentType && contentType.includes('application/json')) {
                const errorData = await response.json();
                throw new Error(errorData.message || 'Failed to sign up');
            } else {
                const errorText = await response.text();
                throw new Error(errorText || 'Failed to sign up.');
            }
        }

        if (contentType && contentType.includes('application/json')) {
            const { token, userDTO } = await response.json();
            if (token && typeof token === 'string' && token.trim() !== '' && userDTO) {
                return { success: true, token, userDTO };
            } else {
                throw new Error('Invalid token or user data received');
            }
        } else {
            throw new Error('Unexpected response format');
        }
    } catch (error) {
        console.error('Error:', error.message);
        throw new Error(error.message || 'Failed to sign up');
    }
};

export { signupService };

const signinService = async (identifier, password) => {
    try {
        const response = await fetch('http://localhost:8080/api/v1/auth/signin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ identifier, password }),
        });

        const contentType = response.headers.get('Content-Type');

        if (!response.ok) {
            if (contentType && contentType.includes('application/json')) {
                const errorData = await response.json();
                throw new Error(errorData.message || 'Failed to sign in');
            } else {
                const errorText = await response.text();
                throw new Error(errorText || 'Failed to sign in');
            }
        }

        if (contentType && contentType.includes('application/json')) {
            const { token, userDTO } = await response.json();
            if (token && typeof token === 'string' && token.trim() !== '' && userDTO) {
                delete userDTO.password;
                return { success: true, token, userDTO };
            } else {
                throw new Error('Invalid token or user data received');
            }
        } else {
            throw new Error('Unexpected response format');
        }

    } catch (error) {
        console.error('Error:', error.message);
        throw new Error(error.message || 'Failed to sign in');
    }
};

export { signinService };
