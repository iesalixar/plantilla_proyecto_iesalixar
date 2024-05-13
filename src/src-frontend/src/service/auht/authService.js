const signupService = async (email, password, username, age) => {
    try {
        const response = await fetch('http://localhost:8080/api/v1/auth/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, password, username, age, }),
        });

        if (!response.ok) {
            throw new Error('Failed to sign in');
        }
        const data = await response.json();
        return data.token;
    } catch (error) {
        throw new Error('Failed to sign in');
    }
};

export { signupService };

const signinService = async (email, password) => {
    try {
        const response = await fetch('http://localhost:8080/api/v1/auth/signin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, password }),
        });

        if (!response.ok) {
            throw new Error('Failed to sign in');
        }
        const data = await response.json();
        return data.token;
    } catch (error) {
        throw new Error('Failed to sign in');
    }
};

export { signinService };

