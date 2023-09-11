import {useEffect, useState} from 'react'
import ApiClient from "./services/ApiClient.js";
import './App.css'

function App() {
  const [vault, setVault] = useState();

  const [loading, setLoading] = useState(true);
//  const [items, setItems] = useState([]);
  // setItems(vault.items);

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetch(ApiClient.SERVER_URL+ApiClient.GET_ITEMS);
      setVault(await JSON.parse(await data.text()));
      setLoading(false);
    }
    fetchData()
      .catch(console.error);
  }, [])

  if (loading) {
    return <div>LOADING...</div>
  }
  return (
    <>
      <h1>CSGO VAULT</h1>
      <div>
        {vault.map((i) => (
          <div>
            <div>
              <label>ID</label>
              <br/>
              {i.id}</div>
            <div><
              label>NAME</label>
              <br/>
              {i.name}
            </div>
          </div>
        ))}
      </div>
    </>
  )
}

export default App
