'use client';

import { useEffect, useState } from 'react';
import { useRouter, useSearchParams } from 'next/navigation';

import Form from '@components/Form';

const EditUser = ({ params }: any) => {
    const router = useRouter();
    const searchParams = useSearchParams();
    const [userId, setUserId] = useState(params.id);

    const [submitting, setSubmitting] = useState(false);
    const [user, setUser] = useState({
        id: '',
        name: '',
        surname: '',
        email: '',
        password: '',
    });

    useEffect(() => {
        const getUserDetails = async () => {
            const response = await fetch(`http://localhost:8080/api/v1/users/${userId}`);
            const data = await response.json();
            setUser({
                id: data.id,
                name: data.name,
                surname: data.surname,
                email: data.email,
                password: ''
            })
        }
        if (userId) getUserDetails();
    }, [userId])

    const updateUser = async (e: any) => {
        e.preventDefault();
        setSubmitting(true);

        if (!userId)
            return alert('User ID not found');

        try {
            const response = await fetch('http://localhost:8080/api/v1/users',
                {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        id: user.id,
                        name: user.name,
                        surname: user.surname,
                        email: user.email,
                        password: user.password
                    })
                })

            if (response.ok) {
                router.push("/users");
            }
        } catch (error) {
            console.log(error);
        } finally {
            setSubmitting(false);
        }
    }

    return (
        <Form
            type="Edit"
            user={user}
            setUser={setUser}
            submitting={submitting}
            handleSubmit={updateUser}
        />
    )
}

export default EditUser