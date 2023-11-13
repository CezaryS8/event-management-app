import Link from "next/link"
import './globals.css'

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      {/* <body className={inter.className}>{children}</body> */}
      <body>
        <main>
          <nav>
            <Link href="/">
              Home
            </Link>
            <Link href="/users">
              Users
            </Link>
          </nav>
          {children}
        </main>
      </body>
    </html>
  )
}
