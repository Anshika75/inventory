import React from 'react'
import Header from './Header'
import Sidebar from './Sidebar'
import ProductDetailPage from '../Product/ProductDetailPage/ProductDetailPage'

export default function ProductMainPage() {
  return (
    <>
        <div className="flex w-full h-screen overflow-hidden">
            <Sidebar/>
            <div className="flex flex-col w-full  overflow-y-scroll">
                <Header/>
                <ProductDetailPage/>
            </div>
        </div>
    </>
  )
}
