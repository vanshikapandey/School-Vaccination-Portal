import { useEffect, useState } from 'react'
import axios from 'axios'

export default function Drives() {
  const [drives, setDrives] = useState([])
  const [formData, setFormData] = useState({
    vaccineName: '',
    date: '',
    availableDoses: '',
  })

  const [editId, setEditId] = useState(null)

  const fetchDrives = () => {
    axios.get('http://localhost:8080/api/drives',{
       withCredentials: true
    })
      .then(res => setDrives(res.data))
  }

  useEffect(() => {
    fetchDrives()
  }, [])

  const handleSubmit = (e) => {
    e.preventDefault()
    const method = editId ? 'put' : 'post'
    const url = editId
      ? `http://localhost:8080/api/drives/${editId}`
      : `http://localhost:8080/api/drives`

    axios[method](url, formData,{
      withCredentials: true,
    })
      .then(() => {
        setFormData({ vaccineName: '', date: '', availableDoses: ''})
        setEditId(null)
        fetchDrives()
      })
      .catch(err => alert(err.response.data.message || 'Error saving drive'))
  }

  const handleEdit = (drive) => {
    setEditId(drive.id)
    setFormData({
      vaccineName: drive.vaccineName,
      date: drive.date,
      availableDoses: drive.availableDoses,
    })
  }

  return (
    <div className="p-6 space-y-6">
      <h1 className="text-2xl font-semibold">Vaccination Drive Management</h1>

      <form className="bg-white p-4 rounded shadow space-y-4" onSubmit={handleSubmit}>
        <h2 className="text-lg font-medium">{editId ? 'Edit Drive' : 'Create Drive'}</h2>
        <input name="vaccineName" value={formData.vaccineName} onChange={e => setFormData({ ...formData, vaccineName: e.target.value })}
          placeholder="Vaccine Name" className="border p-2 w-full" required />
        <input type="date" name="date" value={formData.date} onChange={e => setFormData({ ...formData, date: e.target.value })}
          className="border p-2 w-full" required />
        <input type="number" name="availableDoses" value={formData.availableDoses} onChange={e => setFormData({ ...formData, availableDoses: e.target.value })}
          placeholder="Available Doses" className="border p-2 w-full" required />


        <button type="submit" className="bg-green-600 text-white px-4 py-2 rounded">
          {editId ? 'Update Drive' : 'Create Drive'}
        </button>
      </form>

      <div className="bg-white p-4 rounded shadow">
        <h2 className="text-lg font-medium mb-2">Scheduled Drives</h2>
        <table className="w-full table-auto">
          <thead className="bg-gray-200">
            <tr>
              <th className="p-2">Vaccine</th>
              <th className="p-2">Date</th>
              <th className="p-2">Doses</th>
            </tr>
          </thead>
          <tbody>
            {drives.map(drive => {
              const isPast = new Date(drive.date) < new Date()
              return (
                <tr key={drive.id} className="border-t">
                  <td className="p-2">{drive.vaccineName}</td>
                  <td className="p-2">{drive.date}</td>
                  <td className="p-2">{drive.availableDoses}</td>
                  <td className="p-2">
                    {isPast
                      ? <span className="text-gray-500">Expired</span>
                      : <button onClick={() => handleEdit(drive)} className="text-blue-600">Edit</button>}
                  </td>
                </tr>
              )
            })}
          </tbody>
        </table>
      </div>
    </div>
  )
}
