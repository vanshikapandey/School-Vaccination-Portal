import { useEffect, useState } from 'react'
import axios from 'axios'

export default function Students() {
  const [students, setStudents] = useState([])
  const [formData, setFormData] = useState({
    studentId: '',
    name: '',
    studentClass: ''
  })
  const [file, setFile] = useState(null)
  const [search, setSearch] = useState('')

  const fetchStudents = () => {
    const url = search
      ? `http://localhost:8080/api/students/search?name=${search}`
      : `http://localhost:8080/api/students`
    axios.get(url,{
       withCredentials: true,
       headers: {
    'Cache-Control': 'no-cache'
  }
    }).then(res => setStudents(res.data))
  }

  useEffect(() => {
    fetchStudents()
  }, [search])

  const handleChange = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }))
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    axios.post('http://localhost:8080/api/students', formData, {
      withCredentials: true,
    })
      .then(() => {
        setFormData({ studentId: '', name: '', studentClass: '' })
        fetchStudents()
      })
  }

  const handleUpload = () => {
    const form = new FormData()
    form.append('file', file)
    axios.post('http://localhost:8080/api/students/upload', form,{
      withCredentials: true
    })
      .then(() => {
        setFile(null)
        fetchStudents()
      })
  }

  return (
    <div className="p-6 space-y-6">
      <h1 className="text-2xl font-semibold">Student Management</h1>

      <form className="space-y-4 bg-white p-4 rounded shadow" onSubmit={handleSubmit}>
        <h2 className="text-lg font-medium">Add Individual Student</h2>
        <input name="studentId" value={formData.studentId} onChange={handleChange}
          placeholder="Student ID" className="border p-2 w-full" required />
        <input name="name" value={formData.name} onChange={handleChange}
          placeholder="Name" className="border p-2 w-full" required />
        <input name="studentClass" value={formData.studentClass} onChange={handleChange}
          placeholder="Class (e.g., 6A)" className="border p-2 w-full" required />
        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">Add Student</button>
      </form>

      <div className="bg-white p-4 rounded shadow">
        <h2 className="text-lg font-medium mb-2">Bulk Upload (CSV)</h2>
        <input type="file" accept=".csv" onChange={e => setFile(e.target.files[0])} />
        <button onClick={handleUpload} className="bg-green-600 text-white px-4 py-1 rounded ml-4">
          Upload CSV
        </button>
      </div>

      <div className="bg-white p-4 rounded shadow">
        <div className="flex justify-between items-center mb-2">
          <h2 className="text-lg font-medium">All Students</h2>
          <input type="text" value={search} onChange={e => setSearch(e.target.value)}
            placeholder="Search by name" className="border px-3 py-1" />
        </div>
        <table className="table-auto w-full text-left">
          <thead className="bg-gray-200">
            <tr>
              <th className="p-2">ID</th>
              <th className="p-2">Student ID</th>
              <th className="p-2">Name</th>
              <th className="p-2">Class</th>
            </tr>
          </thead>
          <tbody>
            {students.map(s => (
              <tr key={s.id} className="border-t">
                <td className="p-2">{s.id}</td>
                <td className="p-2">{s.studentId}</td>
                <td className="p-2">{s.name}</td>
                <td className="p-2">{s.studentClass}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
