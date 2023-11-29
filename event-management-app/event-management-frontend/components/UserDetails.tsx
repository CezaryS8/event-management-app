import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import UserCard from './UserCard';

// Model
const fetchUserDetails = async (userId) => {
  const response = await fetch(`http://localhost:8080/api/v1/users/${userId}`);
  const data = await response.json();
  return data;
};

// View
const UserDetailsView = ({ user, userCards }) => (
  <section className="w-full">
    <h1 className="head_text text-center">
      <span className="blue_gradient">
        {user.name} {user.surname}
      </span>
    </h1>
    <p className="text-center">{user.email}</p>

    <div className="mt-16 user_layout">
      {userCards}
    </div>
  </section>
);

// Controller
const UserDetails = () => {
  const router = useRouter();
  const { userId } = router.query;
  const [user, setUser] = useState({});
  const [userCards, setUserCards] = useState([]);

  useEffect(() => {
    const fetchUserData = async () => {
      const userData = await fetchUserDetails(userId);
      setUser(userData);

      // Assuming you have a list of related users in the userData
      setUserCards(userData.relatedUsers.map((relatedUser) => (
        <UserCard key={relatedUser.id} user={relatedUser} />
      )));
    };

    if (userId) {
      fetchUserData();
    }
  }, [userId]);

  return (
    <UserDetailsView user={user} userCards={userCards} />
  );
};

export default UserDetails;



// import React from 'react'

// const UserDetails = ({ user, handleEdit, handleDelete }) => {
//   return (
//     <section className="w-full">
//       <h1 className="head_text text-center">
//         <span className="blue_gradient">
//           {user.name} {user.surname}
//         </span>
//       </h1>
//       <p className="text-center">{user.email}</p>

//       <div className="mt-16 user_layout">
//         {data.map((user) => (
//           <UserCard
//             key={user.id}
//             user={user}
//           />
//         ))}
//       </div>
//     </section>
//   )
// }

// export default UserDetails