import { useEffect, useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

export default function Dashboard() {
  const [summary, setSummary] = useState(null)
  const navigate = useNavigate()

  useEffect(() => {
    axios.get('http://localhost:8080/api/dashboard',{
      withCredentials: true
    })
      .then(res => setSummary(res.data))
      .catch(err => console.error('Error loading dashboard', err))
  }, [])

  if (!summary) return <p className="p-6">Loading...</p>

  return (
    <div className="p-6 space-y-6 bg-gray-100 min-h-screen">
      <h1 className="text-2xl font-semibold">Dashboard</h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div className="bg-white p-4 rounded shadow">
          <p className="text-gray-500">Total Students</p>
          <p className="text-2xl font-bold">{summary.totalStudents}</p>
        </div>

        <div className="bg-white p-4 rounded shadow">
          <p className="text-gray-500">Vaccinated</p>
          <p className="text-2xl font-bold">{summary.vaccinatedCount}</p>
        </div>

        <div className="bg-white p-4 rounded shadow">
          <p className="text-gray-500">Vaccination %</p>
          <p className="text-2xl font-bold">{summary.vaccinationPercentage}%</p>
        </div>
      </div>

      <div className="bg-white p-4 rounded shadow mt-6">
        <h2 className="text-lg font-semibold mb-2">Upcoming Vaccination Drives</h2>
        {summary.upcomingDrives.length === 0 ? (
          <p>No upcoming drives.</p>
        ) : (
          <ul className="space-y-2">
            {summary.upcomingDrives.map(drive => (
              <li key={drive.id} className="border p-2 rounded">
                <p><strong>{drive.vaccineName}</strong> on {drive.date}</p>
                <p>Doses: {drive.availableDoses}, Classes: {drive.applicableClasses.join(', ')}</p>
              </li>
            ))}
          </ul>
        )}
      </div>

      <div className="flex space-x-4 mt-8">
        <button onClick={() => navigate('/students')} className="bg-blue-500 text-white px-4 py-2 rounded">
          Manage Students
        </button>
        <button onClick={() => navigate('/drives')} className="bg-green-500 text-white px-4 py-2 rounded">
          Manage Drives
        </button>
        <button onClick={() => navigate('/reports')} className="bg-purple-500 text-white px-4 py-2 rounded">
          View Reports
        </button>
      </div>
    </div>
  )
}
