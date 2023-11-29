'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';

import Form from '@components/Form';

const page = () => {
    const router = useRouter();

    const [submitting, setSubmitting] = useState(false);
    const [user, setUser] = useState({
        name: '',
        surname: '',
        email: '',
        password: '',
    });

    const createUser = async (e: any) => {
        e.preventDefault();
        setSubmitting(true);

        try {
            const response = await fetch('http://localhost:8080/api/v1/users',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
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
            type="Create"
            user={user}
            setUser={setUser}
            submitting={submitting}
            handleSubmit={createUser}
        />
    )
}

export default page