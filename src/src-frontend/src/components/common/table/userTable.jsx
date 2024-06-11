import React, { useState, useEffect } from 'react';
import { fetchAllUsers } from '../../../service/userService';
import { useAuthContext } from '../../../contexts/AuthProvider';
const UserTable = () => {
    const [users, setUsers] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const { userData } = useAuthContext();
    const token = userData.token;

    const fetchUsers = async () => {
        try {
            const response = await fetchAllUsers(token);
            setUsers(response);
        } catch (error) {
            console.error('Error getting users:', error.message);
        }
    };

    useEffect(() => {
        fetchUsers(currentPage);
    }, [currentPage]);

    const nextPage = () => {
        setCurrentPage(currentPage + 1);
    };

    const prevPage = () => {
        if (currentPage > 1) {
            setCurrentPage(currentPage - 1);
        }
    };

    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Email</th>
                        {/* Add more headers if needed */}
                    </tr>
                </thead>
                <tbody>
                    {users.map((user) => (
                        <tr key={user.id}>
                            <td>{user.name}</td>
                            <td>{user.age}</td>
                            <td>{user.email}</td>
                            {/* Render more user data if needed */}
                        </tr>
                    ))}
                </tbody>
            </table>
            <div>
                <button onClick={prevPage} disabled={currentPage === 1}>Prev</button>
                <button onClick={nextPage}>Next</button>
            </div>
        </div>
    );
};

export default UserTable;
