package org.triathlongirls.springboot.service.gcil;
import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;

@Service
public class OcrService {
    public String detectDocumentText(String filePath) {
        try {
            List<AnnotateImageRequest> requests = new ArrayList<>();

            String base64String = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAfQAAADSCAYAAAC8VDIVAAAAAXNSR0IArs4c6QAAH1FJREFUeF7tnT2OFUcXhhtpcmAFQOTEEib/JGAFtiNjySMMG8Begc0KzIROBqMhMBGeDRhGXgAmsOQMswIgc8an99qN79ypvl1dder3Pi0hJKifc55zbr1d1dXV596/f/9+4IIABCAAAQhAoGkC5xD0puOH8RCAAAQgAIEVAQSdRIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEIAABCAAAQSdHIAABCAAAQh0QABB7yCIuAABCEAAAhBA0MkBCEAAAhCAQAcEEPQOgogLEGiJwJ07d4ZffvllePv27fDNN98MP/zwQ0vmYysEqiWAoFcbGgyDQH8EJOSff/75KceePn06fPbZZ/05i0cQyEwAQc8M3Kq7/f394fHjx8Pe3t5w69at4ejoyKpp2oFAMgLff//9cP/+/VPtf/fdd4P+nQsCEIgjgKDH8StS++HDh8Pdu3dP9X14eDhoKZMLAjUTQNBrjg62tU4AQW8wggyKDQYNk1cEyF0SAQLpCCDo6dgma5lBMRlaGk5MgNxNDJjmd5oAgt5g+BkUGwwaJjNDJwcgkJgAgp4YcIrmEfQUVGkzBwFyNwdl+thVAgh6g5FnUGwwaJjMDJ0cgEBiAgh6YsApmkfQU1ClzRwEyN0clOljVwkg6A1GnkGxwaBhMjN0cgACiQkg6IkBp2geQU9BlTZzECB3c1Cmj10lgKA3GHkGxQaDhsnM0MmBWQJff/31cHx8vDrnX8cB6xCtCxcuzNajwD8EEPQGMwFBbzBomJxU0HWc7Hh8rERAwsDVFgHXOf+ffPLJ8OLFi7YcKWgtgl4QfmjXCHooOeqVJmCZu5rJSQSeP38+/PXXX6dce/Xq1XD58uXS7tL/AgKu3FD1Z8+eDTdu3FjQ0u4WRdAbjL3loNig+5jcMIGY3P3999+Hk5OTlYBLyLddfPClvST56aefnN+j0Od19ZldrnkCCPo8o+pKxAyK1TmDQTtFYEnuatatWbgEXH/0XNX3YobuS6qucpqJ66Zt/eLmzD9GCLo/q2pKLhkUqzEaQyDg8XEWifajR48GzdY0Iw+57t27Nzx48CCkKnUKE2BsiwsAgh7Hr0htkr4Idjo1IDCVu9rEdnBwsBLyJTPxdZOuXr262hin3dFcbRJgbIuLG4Iex69IbZK+CHY6NSDgyl1tXtvc1ObT1aVLl1abpSTg+pvXm3yo1V2GsS0uPgh6HL8itUn6Iti76XR/f394/PjxVn+++uqr4ejoyNTnqV3Mvp2cP39+JdzjH73SxNUXAca2uHgi6HH8itQm6Ytg76JTvaN99+5dL18ODw+du469KjsKhQi6ltG1HC8RR8BDybdTj7EtLlYIehy/IrVJ+iLYu+h0iaha7y727VszcS2jqzzvkneRdt5OMLZ5o3IWRNDj+BWpTdIXwd5FpzXP0PVMXLNxvXPM8/Au0m2xE4xti5GdqrDzgn7nzp3VIRWhO2td+LU0qIEz1RIhSR+X9Ltee+4Z+t7e3nDr1q1sz9CvX7++EnF2p+96Zg6rVRkd47t+Wa8U9Ux5pwXddXawVbA1w9AZxCmWDEl6qyjRTk4CU0vuDNg5o1B3X4xtcfHZaUH3faYXijjVkYUkfWhEqFeSAIJekn4bfTO2xcVppwV96uzgOKT/1U418yDprSJEOzkJ7Jqgrz/aSPEaYM7Y5eqLsS2O9E4LutBpE46W3t+9exdH0lEbQTdHSoMNE9glQXdtPrR+DbDhVJg0HUGPi+rOC3ocvv9q50zEnH1Z8aEdCOySoE/5+v79exJhCwHGtrj0QNDj+H2onTMRc/ZlhIdmKiCglSjtIB4/eqK3MLSzXB8zyfGa2K4LeqoVuwpSy8wExrY4lAh6HD8E3YgfzaQloEdL+oqZ65KYa4k49WtjCPp3q9eyuKYJIOhx2YGgx/FD0I340Uw6Ar5vc6icZpEpLp338PPPPw9///33meZ7nLkiTGFZBLcwbmMtBD2OXzWCHuNGqoNEYmyirg0BfcXsypUr3o1pJq/XLS2X4OfOe0DQvcPTfUEEPS7ECHocvy4EfXSCXbhGyVBRM9uW2qfMtD7pcG6FAEGvKGEKm4KgxwUAQY/j15Wg9ziwhoZ3/UhgHUuqWWtr1/Pnz4ebN2+eMfv27durZ7ny6/j42OmWZujPnj0zOb4YQf8HMb+v+V8Qgj7PaFsJBD2OX1eCzgz9n3C6loifPn2afNPY0lScO5N9aXtT5WMPRZk7wKlHoUOYwrIPbmHcxloIehy/LgSdZ+ink6CFQWXJV9MsUjzmZm/uOT6CbhGhPtpo4bdXM2kE3Sg6ORMxZ19GeJpqpgW+c8vYKYDHHIpy48aN4eTkxGkWgp4iWm222cJvr2ayCLpRdHImYs6+jPA01UwLfHPP0P/3v/8Nv/32W3Acty27I+jBWLur2MJvr2boCLpRdHImYs6+jPA01UwrfHM9Qx+Dp5sI7ZoPvbTRzvXNBAQ9lGh/9Vr57dVKHkE3ikzORMzZlxGepprZdb46GlZL5JviK0F+8eLFcPny5aB43rp1a3jy5MmZugh6EM4uK+36by82qAh6LMF/6+dMxJx9beLRYK8B3fLgEaMQmDVTkq+ZE5ENTS2RS+j1OlvIpZPivvzyyzNVv/jii9Upcj1d5FBYNOEWxm2shaDH8ftQO2ci5uxrE8/FixeHt2/frkRdM6uYJVgj9ObNlORr7kxEg1OH0kjQJewh17lz585U++ijj4Y///wzpLlq65BDYaGBWxg3BD2O25naORMxZ1/rjrrez459rmocBpPmSvE1Md6wEd246dS4169fn2o1ZpauY2j1Gtvm9erVq+ClfEOXzZoih8JQwi2MG4Iex20nBX3qVamYGZtxGEyaY1D5D+PUaXNv3rwJeuwyNevXSXw6ua6XixwKiyTcwrgh6HHcEPQ1ApbHhBqHJag5BpXT2PR4ZXOWHnoTN3VTqD40S+/lIofCIgm3MG4Iehw3BH2DgJZmNcj3sFmu10Fl3a8lj0r0nfTNM99DZ9Sa8evRzcHBwZnfkHbQK49irlrO4O81h2Ji41MXbj6UpsuwKS6O34faORMxZ1/reKZmV2MZDfw687z1qxTflNxcPvk+t07BQ8L98uXLUy7fu3dvePDgQTCGms7gT8EsGExDFeEWFywEPY4fgr7BL3TmZhQGk2Z6G1SmzlL3ff87BQ/Xa3ESec3SQ68UdvZgS6gPJerVFMMS/sf2iaDHEvy3fs5EzNnXkhn6WNZi6dQoLEHNlOIbZKxHJc16v/322zMlfWfornPYY78+N3WT4WuTy+2a4laTLR4pUk0RuMWFAkGP4/ehtnbobj4X9J0BLTWhVNLPLbmPfsTOtJbysC5fiq+1H2N7Ln/0TXTNkn0u1/J46Ka49f5c7S55tr9pe01xq8kWnxjXUgZucZFA0OP4fajtmsVYDHo1zURcP7bz5893dz53b4NKrD+uw2BCX1ubW/FZcqOBoBsNXhU1E5urFblSxBQE3Qj7tWvXBh2Lun7tgqBrI5N2Lm9ucBKHmOVTo7AENdPboBLjj2uj2aVLl5yHwyyF7XrHPWZ1J8bPpbbPla/Jljlba/p/uMVFA0GP4/ehdqpZTO0zdD1W0O523dBsXjEnihmFJaiZ3gaVGH8+/vjj4Y8//jjF8ebNm8Ovv/4axHa9kk6i01HCm1fod9dj/Ix2ZqOBmmyx9i1le3CLo4ugx/Fb1dbMfFPQtBStASvFVSrpt/U79Xy9xV3vpfimyBW1GerP1Clxlh9TcR1aE7qpMtTPFNxrsiWFf6nahFscWQQ9jt+qtusVnOvXr6+WolNcpZJ+W79T537roBktvbd04EwpvilyJVTQFU/dpLrOXbfc7Gm596SmuNVkS6q8StEu3OKoIuhx/Fa1c+5wDx2gDdycnelNzehaO3Cmt0ElxB9XTo85ZCnorlPoQl+JC/HT4nfhaqMmW1L5mKJduMVRRdDj+K1qp3hPd5tZpZLep9+pj2+EDtIG4VnchI+fixv9t4JmvI8ePVodf6pHNVq5kHhKJFNdS/1xbYRbt81S0JfaVuPvAkG3y1zLfLCzqp2WEHSDWI3fCF9vKuUO71JJ79Pv+K30d+/enSLb0tK7j59L00ZnoUsop979TvVGxNIVHd1waKl92/4PBH0++hY5pBu++/fvr/Im9aXfp95Ykd0lLwtuJe0v3TeCHhmBqROvQnfq+phTKul9+52a4bWy9O7r51ysJIo6bEgi7noWnWrWu2mXyx9tRtPKwKeffrr6DrnsOzk5WQ3oOW21Yr30xmUudrH/H+uXckffjk+1sXbKv5Q3lj5MY7n59NFzGQQ9Mrqu58YpN8SVHLiW/Nhcz0ZlewtL70v8dKWPBHEUct8B2XLW6yPoMWlvaWss63U/LNuK4WPxG506rjfWrrn6MQf7zLXt8/81xdDH3trKIOiREXHtcNesJ+UyWamkX9Jvy0vvS/xcTx/d3On5uO+Rqut1U97ouPzxTXvXSYAI+jy90BwaW46J2bx10yWYocfQK18XQY+MQewPN6T7En2GzDpaXXpfylcirhnV5kmBU7HVSWs6EU03ANprEPvZ0Lkcmnr7YK6exPzWrVvDjz/+eKoogj5HLvzd/7Fl10Rhvte4EqVn5yFjTJzH/dVG0CNjunTwj+xuVb1En6H9trj07sN3fD4uIfddVtfKjd4CEJPcl+yUX5ubFafs0GMjiYr+aGPW+oWgz0fPJ4fmWlGu6KY41U3f+k1DLQdAWXCb49rz/yPokdHN/Q56qLBGuhl8I9Hi0vu2QUUbxzQQaqD1EXLNcjUwK0+0+ayGS8/4Zf/46two8hJxrRzIXv2dI9csB3DLtmLjVJMt677cuXPnQ+4qJyXkNV21cquJ0TZbEPTISOV+Bz3HIDuFJPTH1trSu8XzSwm5Bkz9aemUvM3Yh8bc92dl2b5lW772W/9WYvvdVt/1O0y5dyPEl5piGGJ/6ToIemQEXO+gh55F7WtKqaSP6belpfcYQb969epKxDXL7eGKibmP/5btW7blY/u2MjXZMtpZo025byBj41p7fQQ9IkKuzUYpP8pS+ocZMyC0tPQeIujaUCQhH5eqI9KqqqoxMfdxxLJ9y7Z8bEfQYymdrV9TDO29S98igh7B2JV8qV9Zk7mlkj6231aW3n0FfVxW12y8lufjEensrBob8zl7LNu3bGvO7rn/r8mW0hOBOVbr/18jtyX2ly6LoEdEoFTytdxvC0vv2wR9fOVMfuhPy8/HfVI/da5Ztm/Zlg8bZuixlJihWxNE0COIlhpAWu63haX3UnwjUjFZ1dQsXB/zCX2FKrWtgqxX+NZfVZzaKZ7DlqVBr9GmTR9asHEp95zlEfQI2q7DH3LsGi2V9Fb91r70buVnRGpVUzU1i5a+hz61cuP6zafmFpIgNdpUs6Cvv+KnvTEPHz6sfo8Mgh7yy1irk/rwB5d5pX6Ylv3WvPRu6WdkehWvnpqFpaC7zj9X+zrONPaa+giT2nUdtJOaW4g/NdpUq6C7Jh16vKY3mGreL4Ogh/wyCtcp9cO07LfmpXdLPwunSnT3qVlYCvrUEbfqQzfeehMh5NpcZt9sgxl6CFV3nW37V+x6CW8p9HFQeI/LaiLoy3hVUTr1IDvlpHW/tS69W/tZRdIEGpGahesch1evXgXPgjR7ev369aS3EvfxjzY4Ts22dC7/y5cvZz8nO3UOf2puIeGs0SafGXqIr6nqWB57nMJGBD0F1cRt6lnO3bt3T/VyeHg46JlPyivFgFDj0nsKP1PGJWXbqVmcO3fujPnv378PdmnqJjG4wYmKemVRy/BTbzmk5hbiT402tSbozNBDMo86swT29/eHx48fD3t7e6svYh0dHc3WiS2QYkCYWnrXzEnPq0q8FpbCz1j2peqnZOF6Lm1xMFPqL5XJRi3vbztEKCW30Fyo0aaWBL2Gr9HNxZ4Z+hwh/v8DgVQDwtSsKvVnRadCm8rPFlMpJQvXM299IEb/HnupDb1SpmVzy0s5KSZzN5opuYX6U6NNLQi6jnMWuxJfSVwaawR9KbFC5Y+Pj1cDnZ7tTQ142vij5fhUV8oBwbU5Sn7EPE8N5ZDSz1CbStVLycK1K936pEX9VjRj19/bnq3P8dWNhuz1Pdo3Jbc5W1u+Ua2RWyjvEvUQ9BLUPfscv7mtAUnLkz5Xyk0bKX9s8k+D5eb3uq1eO/JhN5ZJ6ecSO2ooG8NCN5i6EVUeuw5gyf3p4fFmWH8r3/T31Pfhxw1zm5+T9Y1JDDffPpaWq9Emnxl6yjFtKcPayyPoFUZIA83BwYH3N7c3XUg1q009ILjal296j1jCnutK7WcuPyz6mYpJSNubr3eV+PRwiN0hdWrMoRptQtBDsmu6DoJuyzOoNc0WTk5OVsuC+uM7G5/qLNUdbY4BwfXaUe5Zeg4/gxKlQCVtuHzy5IlJz5t5WeLTwyaOeDRSYw7VaBOC7pFMC4og6AtgWRXVDHxdwLUkaX2lmKXnGBCmdijnOFJ3jEEOP63jnaq9qb0NIf2tv/IzdfJazCtrITalqlNjDtVoE4Jum4EIui3PD62tnwOsZ4UaGLWbW39CBVwbhrTTUm2NB2JMDYwpXrHINSDoueXm7mT5q5uUHFcuP3P4EtvH1DkBS9vVq166kR3z1nXjZrXDfaltKcrXmEM12oSg22Yfgm7Lc9Wa5eEWGgi1uUg3BVOnWrl+qHqt5s2bN6be5RoQpo7wzHWoQy4/TYOTqDGLZ+h67UcCvr5D3PWVtVSPihKh2dpsjTlUo00Ium12Iui2PFetWQyC42zc55vbU4ez6GAW39dsfDDkHBBcS726SdEsfe4dYB9ftpXJ6Wesranrp2Lhen6ee/NjSnapuMXYXKNNCHpMRM/WRdBtea5ac80+5rrRcqPEdzxneqlouZZGrWe0OQeEqVm6/NTz9JRXTj9T+mHRdgoWWnq/du3aGfN6eX4+dVNfegUiRSwtcmy9jRZstPbZsj0E3ZLmMKx2qF+5cmW2Vc3AJd6jiM9WmCmQ44eQo491N6dujFKfIJfbz9jYp6yfgoUrrtYHyqRk4tN2Cm4+/ba+8lQjt1juOesj6Ma0ty23a6OaZpgpjhDM8UPI0cd6OKYeJahMil38Y9+5/TROQdPmUrBwLbfrhEMJfS9XCm6xbGq0adOnFmyMjUPK+gi6Md2pGXrq5bYcP4QcfWyGY2qDYUqeJfw0TkOz5qxZuHa3z325zMyZjA1Zc7MwvUabEHSLyP7XBoJuy3PVWu4jLdVnjh9rjj5c4XD1yww9QeI6mrSOuR5HbR6clOIVyzx0pnux5mbhT402IegWkUXQbSlutFbih5Ojzxx9TAVm/UMeqZdnS/qZNDEDGrdkMXVokPXbGAFumlex5GZlXI02IehW0f2nHWbotjxXrZX44eTo0zUg5zzBLUGonE3mYJnLl9h+rFhoP4R2tm/Ozns6TGadtRW32PjVbhOCbhlhBN2W5r+tlfgx5+pTG5f0XFtfqUq92zxJcDwazcXSw5TiRaxYuNqRcz29e167eFrFMmVStmBjSv9j22aGHkvQUb9EUpboMwG6KpqE5X9hsGAx9d55b6+qIejxP1+LfIu3ot0WEPQEsSuRlCX6TICuiibHr96tGzMe+FOFgRmNiM0rLbFrqd31/YKUGxszInJ2Fcsthf012sSSu22kEXRbnqvWSvxwSvSZAB1NVkYgJq80M79586ZTzFO+dlgDQm3cvHv37ilTDg8PB320qdQVE8tcNrdgYy4WIf0g6CHUZuqUSMoSfSZAR5OVEViaV5qJ69PA2mehTZSuSx9rkdj3fu3v7w+PHz8e9vb2Bn1X/ujoqKjLS2NZwtgWbCzBxbdPBN2X1IJyJZKyRJ8LkFC0UQJTm9lC3enxEJlQFrnrtTBGtGBj7rgt6Q9BX0LLs2yJpCzRpycOijVMwFLQJeban2D5BcCG0WY3vYUxogUbswduQYcI+gJYvkVLJGWJPn15UK5dAusH+sR4oWV2LcNfvnw5phnqRhBoYYxowcaIECSviqAnQJw7Kad2Eve+8ShB6Ghyg8C2D+T4wNKsXL8HHYfMVZZA7nEpxNsWbAzxK1cdBD0B6VxJKSF/9OjRoFmU67UgBD1BcHewSeWZBPn4+NjLe53+ppl4qi8LehlBoTMEco1LMehbsDHGv9R1EfQEhFMmpQZXDaxavtTzyG1Xr6dwJQgZTUKgewIpxyUreC3YaOVrinYQ9ARUrZNSs2/NxPUakM/rPixzJggqTUKgcQLW41IKHC3YmMJvqzYRdCuSa+1YJaXE++DgYDUbdy2pu0xnJ3GCgNIkBDogYDUuWaG4f//+an+FrvELirXZaOVrrnYQ9ASkQ5NSy+k6lGM8enTzy1TbTJWQ6zmn/ly4cCGBVzQJAQi0TCB0XLL2WafluQ4d0lHA+ncJ/frFXiD/CCDo/qy8S7p+OFNfJlt/Hr5EwEdjLl26NOgLaAi5d3goCIGdJFCDoE+JuQIi4daFoIenJ4Iezm6y5tRhHC9evFh9dlRL6T6b2qY60Du9EnF9MIRDOhIEkCYh0CGBkoKuR4YSc417Uxcz9PikQ9DjGZ5pQUvm+iiF5aUl9XEmzuEclmRpCwK7QaCEoEvItQ9o6tXakfy4glnCxp6ij6AniqbEVzvTYy99M3p8n5dn47E0qQ+B3SWQUyx9hXzc+zNujstpY4+ZgKAniqqeh2s5XEvsSy4tp4/f3tbfiPgSepSFAASmCOQQS18hl423b98+szkuh409ZwiCnjC6elYuUd4m6trUpjKahSPgCYNB0xDYcQIpxXJ8xXbqk7nr6DUr1xK8VjE3L9Xf/Gb806dPV+Mj1zwBBH2eUVQJJboS9+XLl6t2JOCauY+zcDa1ReGlMgQg4Elgyds325rULFzjmfYKaXzT3z7nZPi+WqvxUpvnNBGaejvI0+WdK4ag71zIcRgCENhFAq7ZrzisTzDGCYf+fRRuPT7Un1HAfcR7c0bOGRl5Mg5Bz8OZXiAAAQgUJSAhvnjxYjYbfGfk2QzagY4Q9B0IMi5CAAIQEIGpMzIs6SDkljSXtYWgL+NFaQhAAALNEoj9vv2U4xLxcXOva7Nbs8AaMxxBbyxgmAsBCEAghsDS79u7+hq/ea/n7+Mz+BibqGtDAEG34UgrEIAABJojoBn7+DEo7VjXn/E1W826JdY6C2MUbp1SyZs59YYZQa83NlgGAQhAAAIQ8CaAoHujoiAEIAABCECgXgIIer2xwTIIQAACEICANwEE3RsVBSEAAQhAAAL1EkDQ640NlkEAAhCAAAS8CSDo3qgoCAEIQAACEKiXAIJeb2ywDAIQgAAEIOBNAEH3RkVBCEAAAhCAQL0EEPR6Y4NlEIAABCAAAW8CCLo3KgpCAAIQgAAE6iWAoNcbGyyDAAQgAAEIeBNA0L1RURACEIAABCBQLwEEvd7YYBkEIAABCEDAmwCC7o2KghCAAAQgAIF6CSDo9cYGyyAAAQhAAALeBBB0b1QUhAAEIAABCNRLAEGvNzZYBgEIQAACEPAmgKB7o6IgBCAAAQhAoF4CCHq9scEyCEAAAhCAgDcBBN0bFQUhAAEIQAAC9RJA0OuNDZZBAAIQgAAEvAkg6N6oKAgBCEAAAhColwCCXm9ssAwCEIAABCDgTQBB90ZFQQhAAAIQgEC9BBD0emODZRCAAAQgAAFvAgi6NyoKQgACEIAABOolgKDXGxssgwAEIAABCHgTQNC9UVEQAhCAAAQgUC8BBL3e2GAZBCAAAQhAwJsAgu6NioIQgAAEIACBegkg6PXGBssgAAEIQAAC3gQQdG9UFIQABCAAAQjUSwBBrzc2WAYBCEAAAhDwJoCge6OiIAQgAAEIQKBeAgh6vbHBMghAAAIQgIA3AQTdGxUFIQABCEAAAvUSQNDrjQ2WQQACEIAABLwJIOjeqCgIAQhAAAIQqJcAgl5vbLAMAhCAAAQg4E0AQfdGRUEIQAACEIBAvQQQ9Hpjg2UQgAAEIAABbwIIujcqCkIAAhCAAATqJYCg1xsbLIMABCAAAQh4E0DQvVFREAIQgAAEIFAvgf8DPEOd81FUxQYAAAAASUVORK5CYII=";
            String[] strings = base64String.split(",");
            byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
            String path = "/Users/inkyeong/Desktop/test.png";
            File file = new File(path);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(data);
            outputStream.close();

            String imageFilePath = "/Users/inkyeong/Desktop/test.png";
            ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));

            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).setModel("builtin/latest").build();
            AnnotateImageRequest request =
                    AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
            requests.add(request);

            try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();
                client.close();

                for (AnnotateImageResponse res : responses) {
                    if (res.hasError()) {
                        System.out.format("Error: %s%n", res.getError().getMessage());
                        return "Error";
                    }

                    // For full list of available annotations, see http://g.co/cloud/vision/docs
                    TextAnnotation annotation = res.getFullTextAnnotation();
                    for (Page page : annotation.getPagesList()) {
                        String pageText = "";
                        for (Block block : page.getBlocksList()) {
                            String blockText = "";
                            for (Paragraph para : block.getParagraphsList()) {
                                String paraText = "";
                                for (Word word : para.getWordsList()) {
                                    String wordText = "";
                                    for (Symbol symbol : word.getSymbolsList()) {
                                        wordText = wordText + symbol.getText();
                                        System.out.format(
//                                                "Symbol text: %s (confidence: %f)%n",
                                                symbol.getText(), symbol.getConfidence());
                                    }
                                    System.out.format(
//                                            "Word text: %s (confidence: %f)%n%n", wordText, word.getConfidence());
                                    paraText = String.format("%s %s", paraText, wordText));
                                }
                                // Output Example using Paragraph:
//                                System.out.println("%nParagraph: %n" + paraText);
//                                System.out.format("Paragraph Confidence: %f%n", para.getConfidence());
                                blockText = blockText + paraText;
                            }
                            pageText = pageText + blockText;
                        }
                    }
//                    System.out.println("%nComplete annotation:");
//                    System.out.println(annotation.getText());
                    return annotation.getText();
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

}



