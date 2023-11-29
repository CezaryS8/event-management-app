import Link from 'next/link';


const Form = ({
  type, user, setUser, submitting, handleSubmit
}) => {
  return (
    <section className="w-full max-w-full flex-center flex-col">
      <h1 className="head_text text-left">
        <span className="blue_gradient">{type} User</span>
      </h1>
      <p className="desc text-left max-w-md">
        {type} user and let's do amazing things together!
      </p>

      <form
        onSubmit={handleSubmit}
        className="mt-10 w-full max-w-2xl flex flex-col gap-5 glassmorphism"
      >
        <label>
          <span className="font-satoshi font-semibold text-base text-gray-700">Name</span>
        </label>
        <input
          type="text"
          placeholder="Name..."
          value={user.name}
          onChange={(e) => setUser({ ...user, name: e.target.value })}
          required
          className="form_input"
        />

        <label>
          <span className="font-satoshi font-semibold text-base text-gray-700">Surname</span>
        </label>
        <input
          type="text"
          placeholder="Surname..."
          value={user.surname}
          onChange={(e) => setUser({ ...user, surname: e.target.value })}
          required
          className="form_input"
        />

        <label>
          <span className="font-satoshi font-semibold text-base text-gray-700">Email</span>
        </label>
        <input
          type="text"
          placeholder="Email..."
          value={user.email}
          onChange={(e) => setUser({ ...user, email: e.target.value })}
          required
          className="form_input"
        />

        <label>
          <span className="font-satoshi font-semibold text-base text-gray-700">Password</span>
        </label>
        <input
          type="text"
          placeholder="Password..."
          value={user.password}
          onChange={(e) => setUser({ ...user, password: e.target.value })}
          required
          className="form_input"
        />

        <div className="flex-end mx-3 mb-5 gap-4">
          <Link href="/users" className='text-gray-500 text-sm'>
            Cancel
          </Link>
          <button
            type="submit"
            disabled={submitting}
            className="px-5 py-1.5 text-sm bg-black rounded-full text-white"
          >
            {submitting ? `${type}...` : type}
          </button>
        </div>
      </form>
    </section>
  )
}

export default Form