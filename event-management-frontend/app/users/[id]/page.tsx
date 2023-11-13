async function getUser(userId: string) {
    const res = await fetch(
        `http://localhost:8080/api/v1/users/${userId}`,
        {
            next: { revalidate: 5 },
        }
    )
    const data = await res.json();
    return data;
}

export default async function UserPage({ params }: any) {
    const user = await getUser(params.id);

    return (
        <div className="container mx-auto mt-8">
            <h1 className="text-3xl font-bold mb-4">User Details</h1>
            <div className="bg-white p-4 rounded-md shadow-md">
                <h3 className="text-xl font-bold mb-2">{user.name}</h3>
                <h5 className="text-gray-600 mb-2">{user.surname}</h5>
                <p className="text-gray-700">{user.email}</p>
            </div>
        </div>
    )
}