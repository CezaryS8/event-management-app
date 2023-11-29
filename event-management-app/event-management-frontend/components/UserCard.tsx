// UserCardView.js

import React from 'react';
import Image from 'next/image';
import Link from 'next/link';


// Model
const copyUserDetails = (user) => {
  return `${user.name} ${user.surname} ${user.email}`;
};



// View
const UserCardView = ({ user, copied, handleCopy, handleEdit, handleDelete }) => (
  <div className="user_card">
    <div className="flex justify-between items-start gap-5">
      <Link href={`/users/${user.id}`}>
        <div className='flex-1 flex justify-start items-center gap-3 cursor-pointer'>
          <div className="flex flex-col">
            <h3 className="font-satoshi font-semibold text-gray-900">
              {user.name} {user.surname}
            </h3>
            <p className="font-inter text-sm text-gray-500">
              {user.email}
            </p>
          </div>
        </div>
      </Link>

      <div className="copy_btn" onClick={handleCopy}>
        <Image
          alt=''
          src={copied === copyUserDetails(user)
            ? '/assets/icons/tick.svg'
            : '/assets/icons/copy.svg'
          }
          width={12}
          height={12}
        />
      </div>
    </div>
    <div className="mt-5 flex-center gap-4 border-t border-gray-100 pt-3">
      <p
        className="font-inter text-sm green_gradient cursor-pointer"
        onClick={handleEdit}
      >
        Edit
      </p>
      <p
        className="font-inter text-sm orange_gradient cursor-pointer"
        onClick={handleDelete}
      >
        Delete
      </p>
    </div>
  </div>
);

// Controller
import { useState } from 'react';
import { useRouter } from 'next/navigation';

const UserCard = ({ user }) => {
  const router = useRouter();
  const [copied, setCopied] = useState("");

  const handleCopy = () => {
    const details = copyUserDetails(user);
    setCopied(details);
    navigator.clipboard.writeText(details);
    setTimeout(() => setCopied(""), 3000);
  };

  const handleEdit = () => {
    console.log("edit");
    router.push(`/users/update-user/${user.id}`);
  };

  const handleDelete = async () => {
    console.log("delete");
    const hasConfirmed = confirm("Are you sure you want to delete this user?");

    if (hasConfirmed) {
      try {
        await fetch(process.env.NEXT_PUBLIC_API_USERS+user.id, {
          method: 'DELETE',
        });

        router.push('/users');
      } catch (error) {
        console.log(error);
      }
    }
  };

  return (
    <UserCardView
      user={user}
      copied={copied}
      handleCopy={handleCopy}
      handleEdit={handleEdit}
      handleDelete={handleDelete}
    />
  );
};

export default UserCard;









// 'use client';

// import { useState } from 'react';
// import { useRouter } from 'next/navigation';
// import Image from 'next/image';
// import Link from 'next/link';

// const UserCard = ({ user }) => {
//   const router = useRouter();
//   const [copied, setCopied] = useState("");

//   const handleCopy = () => {
//     setCopied(user.name + ' ' + user.surname + ' ' + user.email)
//     navigator.clipboard.writeText(user.name + ' ' + user.surname + ' ' + user.email);
//     setTimeout(() => setCopied(""), 3000);
//   }

//   const handleEdit = () => {
//     console.log("edit");
//     router.push(`/users/update-user/${user.id}`)
// }

// const handleDelete = async () => {
//     console.log("delete");
//     const hasConfirmed = confirm("Are you sure you want to delete this user?");

//     if (hasConfirmed) {
//         try {
//             await fetch(`http://localhost:8080/api/v1/users/${user.id}`, {
//                 method: 'DELETE',
//             });

//             router.push('/users');

//         } catch (error) {
//             console.log(error);
//         }
//     }
// }

//   return (

//     <div className="user_card">
//       <div className="flex justify-between items-start gap-5">
//         <Link href={`/users/${user.id}`}>
//           <div className='flex-1 flex justify-start items-center gap-3 cursor-pointer'>
//             <div className="flex flex-col">
//               <h3 className="font-satoshi font-semibold text-gray-900">
//                 {user.name} {user.surname}
//               </h3>
//               <p className="font-inter text-sm text-gray-500">
//                 {user.email}
//               </p>
//             </div>
//           </div>
//         </Link>

//         <div className="copy_btn" onClick={handleCopy}>
//           <Image
//             alt=''
//             src={copied === (user.name + ' ' + user.surname + ' ' + user.email)
//               ? '/assets/icons/tick.svg'
//               : '/assets/icons/copy.svg'
//             }
//             width={12}
//             height={12}
//           />
//         </div>
//       </div>
//       <div className="mt-5 flex-center gap-4 border-t border-gray-100 pt-3">
//         <p
//           className="font-inter text-sm green_gradient cursor-pointer"
//           onClick={handleEdit}
//         >
//           Edit
//         </p>
//         <p
//           className="font-inter text-sm orange_gradient cursor-pointer"
//           onClick={handleDelete}
//         >
//           Delete
//         </p>

//       </div>
//     </div >
//   )
// }

// export default UserCard