import Link from "next/link";

interface User {
  id: number;
  name: string;
  surname: string;
  email: string;
}

async function getUsers() {
  const res = await fetch(
    'http://localhost:8080/api/v1/users',
    { cache: 'no-store' }
  );
  const users: User[] = await res.json();
  return users;
}

export default async function UsersPage() {
  const users: User[] = await getUsers();
  console.log(users);

  return (
    <div className="container mx-auto mt-8">
      <h1 className="text-3xl font-bold mb-4">Users</h1>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {users.map((user) => (
          <User key={user.id} user={user} />
        ))}
      </div>
    </div>
  );
}

function User({ user }: any) {
  const { id, name, surname, email, password } = user || {};

  return (
    <Link href={`/users/${id}`}>
      <div className="bg-white p-4 rounded-md shadow-md transition duration-300 transform hover:scale-105 cursor-pointer">
        <h2 className="text-xl font-bold mb-2">{name} {surname}</h2>
        <h5 className="text-gray-600">{email}</h5>
      </div>
    </Link>
  )
}
