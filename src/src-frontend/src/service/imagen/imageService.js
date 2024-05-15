const axios = require('axios');
const API_IMAGE_PATH = 'http://localhost:8080/api/v1/image';
// Función para obtener el token del localStorage
function getToken() {
    return localStorage.getItem('token');
}

// Función para configurar el encabezado con el token
function getConfig() {
    return {
        headers: {
            'Authorization': `Bearer ${getToken()}`,
            'Content-Type': 'multipart/form-data'
        }
    };
}

// Servicio para subir una nueva imagen
async function uploadImage(file, activityId) {
    const formData = new FormData();
    formData.append('image', file);
    formData.append('id', activityId);

    try {
        const response = await axios.post(API_IMAGE_PATH, formData, getConfig());
        return response.data;
    } catch (error) {
        console.error('Error uploading image:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener todas las imágenes
async function getAllImages() {
    try {
        const response = await axios.get(API_IMAGE_PATH, getConfig());
        return response.data;
    } catch (error) {
        console.error('Error getting images:', error.response.data);
        throw error.response.data;
    }
}

// Servicio para obtener una imagen por su ID
async function getImageById(id) {
    try {
        const response = await axios.get(`${API_IMAGE_PATH}/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error getting image with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para actualizar una imagen por su ID
async function updateImage(id, file) {
    const formData = new FormData();
    formData.append('image', file);

    try {
        const response = await axios.delete(`${API_IMAGE_PATH}/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error updating image with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Servicio para eliminar una imagen por su ID
async function deleteImage(id) {
    try {
        const response = await axios.delete(`API_IMAGE_PATH/${id}`, getConfig());
        return response.data;
    } catch (error) {
        console.error(`Error deleting image with ID ${id}:`, error.response.data);
        throw error.response.data;
    }
}

// Exportar los servicios del controlador de imágenes
module.exports = {
    uploadImage,
    getAllImages,
    getImageById,
    updateImage,
    deleteImage
};
