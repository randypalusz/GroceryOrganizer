import './App.css'
import Header from './Header.tsx'
import Footer from './Footer.tsx'
import AllGroceries from './AllGroceries.tsx'

function App() {

  return (
    <>
      <Header></Header>
      <AllGroceries key="AllGroceriesList"></AllGroceries>
      <Footer></Footer>
    </>
  );
}

export default App
