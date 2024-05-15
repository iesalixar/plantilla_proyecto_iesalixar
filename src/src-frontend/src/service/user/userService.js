import axios from 'axios';
const API_USER_PATH = 'http://localhost:8080/api/v1/user';

// Función para obtener el token del localStorage
function getToken() {
    return localStorage.getItem('token');
}

// Función para configurar el encabezado con el token
function getConfig() {
    return {
        headers: {
            'Authorization': `Bearer ${getToken()}`
        }
    };
}

// Servicio para crear un usuario
async function createUser(userDTO) {
    try {
        const response = await axios.post(`${API_USER_PATH}`, userDTO, getConfig());
        return response.data;
    } catch (error) {
        console.error('Error creating user:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para crear un usuario administrador
async function createAdmin(userDTO) {
    try {
        const response = await axios.post(`${API_USER_PATH}/add/admin`, userDTO, getConfig());
        return response.data;
    } catch (error) {
        console.error('Error creating admin user:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener todos los usuarios
async function getUsers() {
    try {
        const response = await axios.get('/api', getConfig());
        return response.data;
    } catch (error) {
        console.error('Error getting users:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener un usuario por su ID
async function getUserById(id) {
    try {
        const response = await axios.get(`${API_USER_PATH}/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error getting user with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener los amigos de un usuario por su ID
async function getUserFriends(id) {
    try {
        const response = await axios.get(`${API_USER_PATH}/friends/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error getting friends of user with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para actualizar un usuario por su ID
async function updateUser(id, userData) {
    try {
        const response = await axios.patch(`${API_USER_PATH}/${id}`, userData, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error updating user with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para eliminar un usuario por su ID
async function deleteUser(id) {
    try {
        const response = await axios.delete(`${API_USER_PATH}/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error deleting user with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Exportar los servicios de usuario
export default {
    createUser,
    createAdmin,
    getUsers,
    getUserById,
    getUserFriends,
    updateUser,
    deleteUser
};
