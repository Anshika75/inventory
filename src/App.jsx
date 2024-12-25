import { useState } from 'react'
import './App.css'
import LoginPage from './Login/LoginPage'
import ProductDetailPage from './Product/ProductDetailPage/ProductDetailPage'
import MainPage from './MainPage/MainPage'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      {/* <LoginPage /> */}
      {/* <ProductDetailPage/> */}
      <MainPage/>
    </>
  )
}

export default App
