'use client';
import React from "react";
import UserCard from "./UserCard";
import {useState, useEffect} from "react";

const fetchUsers = async () => {
    const response = await fetch(`http://localhost:8080/api/v1/users`);
    const data = await response.json();
    return data;
};

const Feed = () => {
    const [users, setUsers] = useState<any[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            const usersData = await fetchUsers();
            setUsers(usersData);
        };

        fetchData();
    }, []);

    return (
        <>
            <div>
                { users.map((user) => (
                    <UserCard key={user.id} user={user} />
                ))}
            </div>
        </>
    );
};

export default Feed;