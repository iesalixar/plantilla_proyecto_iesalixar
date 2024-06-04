const API_ACTIVITY_PATH = 'http://localhost:8080/api/v1/activity';

// Servicio para crear una actividad
const createActivity = async (activity, token) => {
    try {
        const response = await fetch(`${API_ACTIVITY_PATH}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(activity)
        });

        const contentType = response.headers.get('Content-Type');

        if (!response.ok) {
            if (contentType && contentType.includes('application/json')) {
                const errorData = await response.json();
                throw new Error(errorData.message || 'Failed to create activity');
            } else {
                const errorText = await response.text();
                throw new Error(errorText || 'Failed to create activity');
            }
        }

        return contentType;

    } catch (error) {
        console.error('Error creating activity:', error.message);
        throw error.message || 'Failed to create activity';
    }
};

export { createActivity };

