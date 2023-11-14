'use client';

import { useState, useEffect } from 'react'

import UserCard from './UserCard';

const UserCardList = ({ data}) => {
  return (
    <div className="mt-16 user_layout">
      {data.map((user) => (
        <UserCard
          key={user.id}
          user={user}
        />
      ))}
    </div>
  )
}

const Feed = () => {
  const [searchText, setSearchText] = useState('');
  const [users, setUsers] = useState([]);

  const handleSearchChange = (e) => {

  }

  useEffect(() => {
    const fetchUsers = async () => {
      const response = await fetch('http://localhost:8080/api/v1/users');
      const data = await response.json();

      setUsers(data);
    }

    fetchUsers();

  }, []);

  return (
    <section className="feed">
      <form className="relative w-full flex-center">
        <input
          type="text"
          placeholder="Search for a user"
          value={searchText}
          onChange={handleSearchChange}
          required
          className="search_input peer"
        />
      </form>

      <UserCardList
        data={users}
      />
    </section>
  )
}

export default Feed