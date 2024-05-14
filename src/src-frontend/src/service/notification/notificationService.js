const axios = require('axios');

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

// Servicio para crear una notificación
async function createNotification(notification) {
    try {
        const response = await axios.post('/api/notification', notification, getConfig());
        return response.data;
    } catch (error) {
        console.error('Error creating notification:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener todas las notificaciones
async function getNotifications() {
    try {
        const response = await axios.get('/api/notification', getConfig());
        return response.data;
    } catch (error) {
        console.error('Error getting notifications:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener notificaciones por ID de usuario
async function getNotificationsByUserId(id) {
    try {
        const response = await axios.get(`/api/notification/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error getting notifications for user with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para actualizar una notificación por su ID
async function patchNotification(id, notification) {
    try {
        const response = await axios.patch(`/api/notification/${id}`, notification, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error patching notification with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para eliminar una notificación por su ID
async function deleteNotification(id) {
    try {
        const response = await axios.delete(`/api/notification/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error deleting notification with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Exportar los servicios del controlador de notificaciones
module.exports = {
    createNotification,
    getNotifications,
    getNotificationsByUserId,
    patchNotification,
    deleteNotification
};
