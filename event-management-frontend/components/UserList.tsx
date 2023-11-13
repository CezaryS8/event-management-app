// use client;
// import { useEffect, useState } from 'react';
// import axios from 'axios';

// interface Event {
//   id: number;
//   name: string;
// }

// const UserList: React.FC = () => {
//   const [events, setEvents] = useState<Event[]>([]);

//   useEffect(() => {
//     const fetchEvents = async () => {
//       try {
//         const response = await axios.get<Event[]>('http://localhost:8080/api/v1/event/');
//         setEvents(response.data);
//       } catch (error) {
//         console.error('Error fetching events:', error);
//       }
//     };

//     fetchEvents();
//   }, []);

//   return (
//     <div>
//       <h1>Event List</h1>
//       <ul>
//         {events.map((event) => (
//           <li key={event.id}>{event.name}</li>
//         ))}
//       </ul>
//     </div>
//   );
// };

// export default UserList;
