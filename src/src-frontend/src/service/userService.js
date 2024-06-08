const API_USER_PATH = 'http://localhost:8080/api/v1/user';

// Función para obtener todos los usuarios
const fetchAllUsers = async (token) => {
    try {
        const response = await fetch(`${API_USER_PATH}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Failed to get users');
        }

        const users = await response.json();
        console.log('Users:', users); // Puedes almacenar esto en el estado si es necesario
    } catch (error) {
        console.error('Error getting users:', error.message);
    }
};

// Función para obtener la información del usuario autenticado
const fetchUserData = async (token, id, updateUser) => {
    try {
        const response = await fetch(`${API_USER_PATH}/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Failed to get user data');
        }

        const user = await response.json();
        updateUser(user);
    } catch (error) {
        console.error('Error getting user data:', error.message);
    }
};
export { fetchAllUsers, fetchUserData }