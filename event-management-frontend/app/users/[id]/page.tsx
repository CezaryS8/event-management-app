'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import UserDetails from '@components/UserDetails';
import UserCard from '@components/UserCard';


const UserPage = ({ params }: any) => {
    const router = useRouter();
    const [user, setUser] = useState([]);
    const [userId, setUserId] = useState(params.id);

    useEffect(() => {
        const fetchUser = async () => {
            const response = await fetch(`http://localhost:8080/api/v1/users/${userId}`);
            const data = await response.json();
            setUser(data);
        }
        console.log(user);

        fetchUser();

    }, []);

    return (
        <UserCard
            user={user}
        />
    )
}

export default UserPage