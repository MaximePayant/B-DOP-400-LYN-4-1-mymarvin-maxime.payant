job('Daily Dose of Satisfaction') {
          steps {
            shell('echo "Hello dear $NAME!"')
            shell('date')
            shell('echo "This is your DDoS number $BUILD_NUMBER."')
          }
        }