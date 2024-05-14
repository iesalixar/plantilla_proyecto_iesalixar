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

// Servicio para crear una actividad
async function createActivity(activity) {
    try {
        const response = await axios.post('/api/activity', activity, getConfig());
        return response.data;
    } catch (error) {
        console.error('Error creating activity:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener todas las actividades
async function getActivities() {
    try {
        const response = await axios.get('/api/activity', getConfig());
        return response.data;
    } catch (error) {
        console.error('Error getting activities:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener una actividad por su ID
async function getActivityById(id) {
    try {
        const response = await axios.get(`/api/activity/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error getting activity with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener actividades asociadas a un usuario por su ID
async function getActivitiesByUserId(userId) {
    try {
        const response = await axios.get(`/api/activity/user/${userId}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error getting activities for user with ID ${userId}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener actividades creadas por un usuario por su ID
async function getCreatedActivities(userId) {
    try {
        const response = await axios.get(`/api/activity/created/${userId}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error getting created activities for user with ID ${userId}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener actividades en las que ha participado un usuario por su ID
async function getInvitedActivities(userId) {
    try {
        const response = await axios.get(`/api/activity/invited/${userId}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error getting invited activities for user with ID ${userId}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para actualizar una actividad por su ID
async function updateActivity(id, activity) {
    try {
        const response = await axios.put(`/api/activity/${id}`, activity, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error updating activity with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para actualizar parcialmente una actividad por su ID
async function patchActivity(id, activity) {
    try {
        const response = await axios.patch(`/api/activity/${id}`, activity, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error partially updating activity with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para eliminar una actividad por su ID
async function deleteActivity(id) {
    try {
        const response = await axios.delete(`/api/activity/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error deleting activity with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Exportar los servicios de actividad
module.exports = {
    createActivity,
    getActivities,
    getActivityById,
    getActivitiesByUserId,
    getCreatedActivities,
    getInvitedActivities,
    updateActivity,
    patchActivity,
    deleteActivity
};
