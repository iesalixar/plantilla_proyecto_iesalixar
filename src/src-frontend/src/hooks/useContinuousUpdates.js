import { useState, useEffect, useCallback } from 'react';

const useContinuousUpdates = (fetchAllUsers, fetchAuthenticatedUser, interval = 30000) => {
    const [allUsers, setAllUsers] = useState([]);
    const [authenticatedUser, setAuthenticatedUser] = useState(null);

    const updateData = useCallback(async () => {
        try {
            const users = await fetchAllUsers();
            setAllUsers(users);

            const user = await fetchAuthenticatedUser();
            setAuthenticatedUser(user);
        } catch (error) {
            console.error('Failed to update data:', error);
        }
    }, [fetchAllUsers, fetchAuthenticatedUser]);

    useEffect(() => {
        updateData(); // Initial call to fetch data

        const intervalId = setInterval(updateData, interval);

        return () => clearInterval(intervalId);
    }, [updateData, interval]);

    return { allUsers, authenticatedUser };
};

export default useContinuousUpdates;
