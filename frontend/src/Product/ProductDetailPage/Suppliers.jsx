import React, { useMemo, useState } from 'react';
import NewSupplierModal from './NewSupplierModal';

function Suppliers() {

  const [suppliers, setSuppliers] = useState([
    { id: 1, name: 'John Doe', contactNumber: '1234567890', deliveryTime: '3 days', successfulOrders: 50, costPerUnit: 10, threshold: 100 },
    { id: 2, name: 'Jane Smith', contactNumber: '9876543210', deliveryTime: '1 day', successfulOrders: 75, costPerUnit: 12, threshold: 150 },
    { id: 3, name: 'Bob Johnson', contactNumber: '5555555555', deliveryTime: '2 days', successfulOrders: 30, costPerUnit: 9, threshold: 80 },
  ]);

  const [searchQuery, setSearchQuery] = useState('');
  const [sortConfig, setSortConfig] = useState({ key: null, direction: 'ascending' });
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [editData, setEditData] = useState({});

  const handleSort = (key) => {
    let direction = 'ascending';
    if (sortConfig.key === key && sortConfig.direction === 'ascending') {
      direction = 'descending';
    }
    setSortConfig({ key, direction });
  };

  const sortedSuppliers = useMemo(() => {
    let sortableSuppliers = [...suppliers];
    if (sortConfig.key) {
      sortableSuppliers.sort((a, b) => {
        if (a[sortConfig.key] < b[sortConfig.key]) {
          return sortConfig.direction === 'ascending' ? -1 : 1;
        }
        if (a[sortConfig.key] > b[sortConfig.key]) {
          return sortConfig.direction === 'ascending' ? 1 : -1;
        }
        return 0;
      });
    }
    return sortableSuppliers;
  }, [suppliers, sortConfig]);

  const filteredSuppliers = useMemo(() => {
    return sortedSuppliers.filter(supplier =>
      supplier.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
      supplier.contactNumber.includes(searchQuery)
    );
  }, [sortedSuppliers, searchQuery]);

  const handleAddSupplier = (newSupplier) => {
    setSuppliers(prevSuppliers => [
      ...prevSuppliers,
      { ...newSupplier, id: prevSuppliers.length + 1 }
    ]);
    setIsModalOpen(false);
  };

  const handleEdit = (supplier) => {
    setEditingId(supplier.id);
    setEditData(supplier);
  };

  const handleSave = () => {
    setSuppliers(prevSuppliers =>
      prevSuppliers.map(supplier =>
        supplier.id === editingId ? { ...supplier, ...editData } : supplier
      )
    );
    setEditingId(null);
    setEditData({});
  };

  const handleCancel = () => {
    setEditingId(null);
    setEditData({});
  };

  const handleDelete = (id) => {
    if (window.confirm('Are you sure you want to delete this supplier?')) {
      setSuppliers(prevSuppliers => prevSuppliers.filter(supplier => supplier.id !== id));
    }
  };

  const handleChange = (e, field) => {
    setEditData({
      ...editData,
      [field]: e.target.value
    });
  };

  return (
    <div className="space-y-6 p-6">
      <div className="flex flex-col sm:flex-row justify-between items-center gap-4">
        <h2 className="text-2xl font-semibold">Suppliers</h2>
        <div className="flex items-center gap-3 w-full sm:w-auto">
          <div className="relative flex-1 sm:flex-initial">
            <input
              type="text"
              placeholder="Search suppliers..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="pl-9 pr-4 py-2 border rounded-md w-full"
            />
            <span className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                <circle cx="11" cy="11" r="8"/>
                <path d="m21 21-4.3-4.3"/>
              </svg>
            </span>
          </div>
          <button 
            onClick={() => setIsModalOpen(true)}
            className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors whitespace-nowrap flex items-center gap-2"
          >
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
              <line x1="12" y1="5" x2="12" y2="19"></line>
              <line x1="5" y1="12" x2="19" y2="12"></line>
            </svg>
            Add Supplier
          </button>
        </div>
      </div>

      <div className="bg-white rounded-lg overflow-x-auto shadow-md">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Supplier Name
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Contact Number
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider cursor-pointer" onClick={() => handleSort('deliveryTime')}>
                Delivery Time
                {sortConfig.key === 'deliveryTime' && (
                  sortConfig.direction === 'ascending' 
                    ? <svg className="inline ml-1" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><polyline points="18 15 12 9 6 15"></polyline></svg>
                    : <svg className="inline ml-1" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><polyline points="6 9 12 15 18 9"></polyline></svg>
                )}
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Successful Orders
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider cursor-pointer" onClick={() => handleSort('costPerUnit')}>
                Cost per Unit
                {sortConfig.key === 'costPerUnit' && (
                  sortConfig.direction === 'ascending' 
                    ? <svg className="inline ml-1" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><polyline points="18 15 12 9 6 15"></polyline></svg>
                    : <svg className="inline ml-1" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><polyline points="6 9 12 15 18 9"></polyline></svg>
                )}
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Threshold
              </th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                Actions
              </th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {filteredSuppliers.map((supplier) => (
              <tr key={supplier.id}>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                  {editingId === supplier.id ? (
                    <input
                      type="text"
                      value={editData.name}
                      onChange={(e) => handleChange(e, 'name')}
                      className="w-full p-1 border rounded"
                    />
                  ) : (
                    supplier.name
                  )}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                  {editingId === supplier.id ? (
                    <input
                      type="text"
                      value={editData.contactNumber}
                      onChange={(e) => handleChange(e, 'contactNumber')}
                      className="w-full p-1 border rounded"
                    />
                  ) : (
                    supplier.contactNumber
                  )}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                  {editingId === supplier.id ? (
                    <input
                      type="text"
                      value={editData.deliveryTime}
                      onChange={(e) => handleChange(e, 'deliveryTime')}
                      className="w-full p-1 border rounded"
                    />
                  ) : (
                    supplier.deliveryTime
                  )}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                  {editingId === supplier.id ? (
                    <input
                      type="number"
                      value={editData.successfulOrders}
                      onChange={(e) => handleChange(e, 'successfulOrders')}
                      className="w-full p-1 border rounded"
                    />
                  ) : (
                    supplier.successfulOrders
                  )}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                  {editingId === supplier.id ? (
                    <input
                      type="number"
                      value={editData.costPerUnit}
                      onChange={(e) => handleChange(e, 'costPerUnit')}
                      className="w-full p-1 border rounded"
                    />
                  ) : (
                    `$${supplier.costPerUnit}`
                  )}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                  {editingId === supplier.id ? (
                    <input
                      type="number"
                      value={editData.threshold}
                      onChange={(e) => handleChange(e, 'threshold')}
                      className="w-full p-1 border rounded"
                    />
                  ) : (
                    supplier.threshold
                  )}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                  {editingId === supplier.id ? (
                    <>
                      <button onClick={handleSave} className="text-green-600 hover:text-green-900 mr-2">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                          <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
                          <polyline points="17 21 17 13 7 13 7 21"></polyline>
                          <polyline points="7 3 7 8 15 8"></polyline>
                        </svg>
                      </button>
                      <button onClick={handleCancel} className="text-red-600 hover:text-red-900">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                          <line x1="18" y1="6" x2="6" y2="18"></line>
                          <line x1="6" y1="6" x2="18" y2="18"></line>
                        </svg>
                      </button>
                    </>
                  ) : (
                    <>
                      <button onClick={() => handleEdit(supplier)} className="text-blue-600 hover:text-blue-900 mr-2">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                          <path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path>
                          <path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path>
                        </svg>
                      </button>
                      <button onClick={() => handleDelete(supplier.id)} className="text-red-600 hover:text-red-900">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                          <polyline points="3 6 5 6 21 6"></polyline>
                          <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                          <line x1="10" y1="11" x2="10" y2="17"></line>
                          <line x1="14" y1="11" x2="14" y2="17"></line>
                        </svg>
                      </button>
                    </>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <NewSupplierModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onSubmit={handleAddSupplier}
      />
    </div>
  );
}

export default Suppliers;

