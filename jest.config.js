module.exports = {
    testEnvironment: 'node',
    verbose: true,
    collectCoverage: true,
    coveragePathIgnorePatterns: ['node_modules', 'index.ts'],
    collectCoverageFrom: ['src/**/*.{ts,tsx}'],
    preset: 'ts-jest',
    transform: {
      '^.+\\.(ts|tsx)?$': 'ts-jest',
    }
  };