'use client';

import Link from 'next/link';
import Image from 'next/image';
import { useState, useEffect } from 'react';

const Nav = () => {
  return (
    <nav className="flex-between w-full mb-16 pt-3">
      <Link href="/" className="flex gap-2 flex-centeer">
        <Image
          src="/assets/images/logo.svg"
          alt="nav-img" 
          width={30}
          height={30}
          className="object-contain"
        />
      <p className="logo_text">Frontend</p>
      </Link>
      <div className="flex gap-3">
        <Link href="/users" className="black_btn">
          Users
        </Link>
        <Link href="/users/create-user" className="outline_btn">
          Create user
        </Link>
      </div>
    </nav>
  )
}

export default Nav