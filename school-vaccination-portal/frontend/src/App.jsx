import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import Students from './pages/Students';
import Drives from './pages/Drives';
import Reports from './pages/Reports';
import Login from './pages/Login';
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/students" element={<Students />} />
        <Route path="/drives" element={<Drives />} />
        <Route path="/reports" element={<Reports />} />
      </Routes>
    </Router>
  )
}

export default App
