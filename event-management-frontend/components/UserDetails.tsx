import React from 'react'

const UserDetails = ({ user, handleEdit, handleDelete }) => {
  return (
    <section className="w-full">
      <h1 className="head_text text-center">
        <span className="blue_gradient">
          {user.name} {user.surname}
        </span>
      </h1>
      <p className="text-center">{user.email}</p>

      <div className="mt-16 user_layout">
        {data.map((user) => (
          <UserCard
            key={user.id}
            user={user}
          />
        ))}
      </div>
    </section>
  )
}

export default UserDetails