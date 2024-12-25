import React from 'react';

function FilterModal({ isOpen, onClose, selectedStatuses, onStatusChange }) {
  if (!isOpen) return null;

  const statuses = [
    { label: 'Delivered', value: 'Delivered' },
    { label: 'On Way', value: 'On Way' },
    { label: 'Confirmed', value: 'Confirmed' },
    { label: 'Pending', value: 'Pending' }
  ];

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-start justify-center pt-20 z-50">
      <div className="bg-white rounded-lg p-6 w-full max-w-md">
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-xl font-semibold">Filter Purchases</h2>
          <button onClick={onClose} className="text-gray-500 hover:text-gray-700">
            ✕
          </button>
        </div>

        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-3">Status</h3>
          <div className="space-y-2">
            {statuses.map((status) => (
              <div key={status.value} className="flex items-center space-x-2">
                <input
                  type="checkbox"
                  id={status.value}
                  checked={selectedStatuses.includes(status.value)}
                  onChange={(e) => onStatusChange(status.value, e.target.checked)}
                  className="rounded border-gray-300"
                />
                <label htmlFor={status.value} className="text-sm font-normal">
                  {status.label}
                </label>
              </div>
            ))}
          </div>
        </div>

        <div className="mt-6 flex justify-end">
          <button
            onClick={onClose}
            className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
          >
            Apply Filters
          </button>
        </div>
      </div>
    </div>
  );
}

export default FilterModal;

