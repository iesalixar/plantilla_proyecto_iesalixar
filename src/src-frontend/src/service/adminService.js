const axios = require('axios');
const API_ADMIN_PATH = 'http://localhost:8080/api/v1/admin';

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

// Servicio para mostrar el dashboard
async function showDashboard() {
    try {
        const response = await axios.get(`${API_ADMIN_PATH}`, getConfig());
        return response.data;
    } catch (error) {
        console.error('Error showing dashboard:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para actualizar un usuario por su ID
async function updateUser(id, userData) {
    try {
        const response = await axios.put(`${API_ADMIN_PATH}/user/${id}`, userData, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error updating user with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para actualizar una notificación por su ID
async function updateNotification(id, notification) {
    try {
        const response = await axios.put(`${API_ADMIN_PATH}/notification/${id}`, notification, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error updating notification with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para actualizar una actividad por su ID
async function updateActivity(id, activity) {
    try {
        const response = await axios.put(`${API_ADMIN_PATH}/activity/${id}`, activity, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error updating activity with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para eliminar un usuario por su ID
async function deleteUser(id) {
    try {
        const response = await axios.delete(`${API_ADMIN_PATH}/user/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error deleting user with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para eliminar una actividad por su ID
async function deleteActivity(id) {
    try {
        const response = await axios.delete(`${API_ADMIN_PATH}/activity/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error deleting activity with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Exportar los servicios del controlador de administrador
module.exports = {
    showDashboard,
    updateUser,
    updateNotification,
    updateActivity,
    deleteUser,
    deleteActivity
};
