'use client';

import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import UserCard from '@components/UserCard';


// Model
const fetchUser = async (userId: any) => {
    const response = await fetch(`http://localhost:8080/api/v1/users/${userId}`);
    const data = await response.json();
    return data;
};

// Controller
const UserPageController = ({ params }: any) => {
    const router = useRouter();
    const [user, setUser] = useState([]);
    const [userId, setUserId] = useState(params.id);

    useEffect(() => {
        const fetchData = async () => {
            const userData = await fetchUser(userId);
            setUser(userData);
        };

        fetchData();
    }, [userId]);

    return <UserCard user={user} />;
};

export default UserPageController;
