import Feed from '@components/Feed';

export default function UsersPage() {
  return (
    <section className="w-full flex-center flex-col">
      <h1 className="head_text text-center">
        Just for learn
        <br className="max-md:hidden" />
        <span className="blue_gradient text-center"> App for PAMIW LAB 6</span>
      </h1>
      
      <p className="desc text-center">This app was created for PAMIW laboratory. Feel free to checkout the code and give feedback.</p>

      <Feed />
    </section>
  );
}