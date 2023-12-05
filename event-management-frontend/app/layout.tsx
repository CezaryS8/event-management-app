import '@styles/globals.css'
import Nav from '@components/Nav';

export const metadata = {
  manifest: "/manifest.json",
  title: "CRUD next.js",
  description: "Application for university project"
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body>
        <div className="main">
          <div className="gradient" />
        </div>
        <main className="app">
          <Nav />
          {children}
        </main>
      </body>
    </html>
  )
}
